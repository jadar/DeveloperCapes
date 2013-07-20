/**
 * Developer Capes by Jadar
 * License: Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * version 2.0
 */
package com.jadarstudios.developercapes.asm;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class DevCapesLoadingPlugin implements IFMLLoadingPlugin {

    @Override
    @Deprecated
    public String[] getLibraryRequestClass() {
        return null;
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[] {"com.jadarstudios.developercapes.asm.DevCapesAccessTransformer"};
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {}

}
