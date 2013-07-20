/**
 * Developer Capes by Jadar
 * License: Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * version 2.0
 */
package com.jadarstudios.developercapes.asm;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_PRIVATE;
import static org.objectweb.asm.Opcodes.ACC_STATIC;

import java.util.Arrays;
import java.util.Iterator;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;


public class DevCapesAccessTransformer implements IClassTransformer {
    
    /** Change this to true if you want extra debug info. */
    private final boolean debug = true;
    
    /** These are the obfuscated and deobfuscated fields and methods to transform.
     * They will be needed to be updated each time there is an obfuscation change.
     * (unless forge has the deobfuscation thing at runtime, but I'm not entirely sure.) 
     */
    private final String[] obfClassToTransform = {"ber"};
    private final String[] classToTransform = {"net.minecraft.client.entity.AbstractClientPlayer"};
    private final String[] obfFieldsToTransform = {"c", "e"};
    private final String[] fieldsToTransform = {"field_110315_c", "field_110313_e"};
    private final String[] obfMethodsToTransform = {};
    private final String[] methodsToTransform = {};


    /**
     * 
     * Run on every class in the classpath. bytes is of the class.
     */
    @Override
    public byte[] transform(String name, String newName, byte[] bytes) {

    	if(contains(classToTransform, name) || contains(obfClassToTransform, name)) {
            
            System.out.println("[DevCapes]: **Transforming AbstractClientPlayer**");
            // object that represents a class.
            ClassNode classNode = new ClassNode();
            // reads a class to the class node.
            ClassReader classReader = new ClassReader(bytes);
            classReader.accept(classNode, 0);

            // iterates through the fields.
            Iterator<FieldNode> fields = classNode.fields.iterator();
            while(fields.hasNext()) {
                // object that represents a field.
            	FieldNode f = fields.next();
                // field name.
            	String n = f.name;
                if(contains(fieldsToTransform, n) || contains(obfFieldsToTransform, n)) {
                    if(debug) 
                    	System.out.println("[DevCapes]: Found field " + f.name + ", making it public.");
                    // changes access.
                    f.access = ACC_PUBLIC;
                }
            }
            
            // iterates through methods
            Iterator<MethodNode> methods = classNode.methods.iterator();
            while(methods.hasNext()) {
                // object that represents a method.
            	MethodNode m = methods.next();
            	// method's name.
                String n = m.name;
                if(contains(methodsToTransform, n) || contains(obfMethodsToTransform, n)) {
                    if(debug) 
                    	System.out.println("[DevCapes]: Found method " + m.name + ", making it public.");
                    
                    // access is in the form of hex. so 0x0001, 0x0002, and so on.
                    // so there can be a private static but they are added together.
                    // that's what this if statement if for.
                    // currently I am not transforming any methods, but its here if I ever need to.
                    if(m.access == ACC_PRIVATE + ACC_STATIC)
                    	m.access = ACC_PUBLIC + ACC_STATIC;
                    else if(m.access == ACC_PRIVATE)
                    		m.access = ACC_PUBLIC;
                    else if(m.access == ACC_STATIC)
                    		m.access = ACC_PUBLIC + ACC_STATIC;
                }
            }
	
            // writes the class and returns the bytes back.
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            classNode.accept(classWriter);
            return classWriter.toByteArray();
        }
        else 
        	// if not the right class then return unmodified bytes.
        	return bytes;

    }

    /**
     * 
     * Useful method to see if an array contains a certain string.
     * 
     * @param array
     * @param key
     * @return
     */
    private boolean contains(final String[] array, final String key) {
        return Arrays.asList(array).contains(key);
    }
}
