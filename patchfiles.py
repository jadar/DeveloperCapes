import os, os.path
import sys, getopt, shlex, subprocess

def main(argv):
   
   inputfile = ''
   outputfile = ''
   try:
      opts, args = getopt.getopt(argv,"hd:",["mcp="])
   except getopt.GetoptError:
      print 'ERROR: patchfiles.py -d <mcpdirectory>'
      sys.exit(2)
   for opt, arg in opts:
      if opt == '-h':
         print 'patchfiles.py -d <mcpdirectory>'
         sys.exit()
      elif opt in ("-d", "--mcp"):
         mcp = arg

   # actually runs the patch patcher.
   print("### Patching AbstractClientPlayer ###")
   print("### If the patch fails then it means that either the file is already patched, or the file is too new for the patch. ###")
   mcp = os.path.abspath(mcp)
   if not os.path.exists(mcp):
       print("MCP does not exist at:" + mcp)
       sys.exit(2)
   apply_patch("AbstractClientPlayer.java.patch", os.path.join(mcp, "src", "Minecraft", "net", "minecraft", "client", "entity", "AbstractClientPlayer.java"), mcp)

# taken from fml.py. written by MinecraftForge people. modified by Jadar.
def apply_patch(patch, target, mcp_dir):
    
    temp = os.path.abspath('tmp')
    fixedPatch = os.path.join(temp, os.path.basename(patch))
    cmd = 'patch -i "%s" "%s" ' % (fixedPatch, target)
    
    if os.name == 'nt':
        applydiff = os.path.abspath(os.path.join(mcp_dir, 'runtime', 'bin', 'applydiff.exe'))
        cmd = '"%s" -uf -i "%s" "%s"' % (applydiff, fixedPatch, target)
        
    if os.sep == '\\':
        cmd = cmd.replace('\\', '\\\\')
    cmd = shlex.split(cmd)
    
    fix_patch(patch, fixedPatch)
    
    process = subprocess.Popen(cmd, cwd=os.path.join(mcp_dir, 'runtime'), bufsize=-1)
    process.communicate()
    
    if os.path.exists(temp):
        os.remove(temp)



# also taken from fml.py. written by MinecraftForge people.
def fix_patch(in_file, out_file, find=None, rep=None):
    #Fixes the following issues in the patch file if they exist:
    #  Normalizes the path seperators for the current OS
    #  Normalizes the line endings
    # Returns the path that the file wants to apply to
    
    in_file = os.path.normpath(in_file)
    if out_file is None:
        tmp_file = in_file + '.tmp'
    else:
        out_file = os.path.normpath(out_file)
        tmp_file = out_file
        dir_name = os.path.dirname(out_file)
        if dir_name:
            if not os.path.exists(dir_name):
                os.makedirs(dir_name)
                
    file = 'not found'
    with open(in_file, 'rb') as inpatch:
        with open(tmp_file, 'wb') as outpatch:
            for line in inpatch:
                line = line.rstrip('\r\n')
                if line[:3] in ['+++', '---', 'Onl', 'dif']:
                    if not find == None and not rep == None:
                        line = line.replace('\\', '/').replace(find, rep).replace('/', os.sep)
                    else:
                        line = line.replace('\\', '/').replace('/', os.sep)
                    outpatch.write(line + os.linesep)
                else:
                    outpatch.write(line + os.linesep)
                if line[:3] == '---':
                    file = line[line.find(os.sep, line.find(os.sep)+1)+1:]
                    
    if out_file is None:
        shutil.move(tmp_file, in_file)
    return file

if __name__ == "__main__":
   main(sys.argv[1:])
   
