import jamiebalfour.zpe.ZPESystemInfoObject;
import jamiebalfour.zpe.core.ZPEFunction;
import jamiebalfour.zpe.core.ZPEModule;
import jamiebalfour.zpe.core.ZPERuntimeEnvironment;
import jamiebalfour.zpe.core.ZPEStructure;
import jamiebalfour.zpe.core.interfaces.ZPECustomFunction;
import jamiebalfour.zpe.core.interfaces.ZPELibrary;
import jamiebalfour.zpe.core.interfaces.ZPEType;
import jamiebalfour.zpe.core.types.ZPEString;
import oshi.SystemInfo;

import java.util.HashMap;
import java.util.Map;

public class Plugin implements ZPELibrary {


  public Plugin(){}


  @Override
  public Map<String, ZPECustomFunction> getFunctions() {
    return new HashMap<>();
  }

  @Override
  public Map<String, Class<? extends ZPEStructure>> getObjects() {
    Map<String, Class<? extends ZPEStructure>> z = new HashMap<>();
    z.put("SystemInfo", ZPESystemInfoObject.class);
    return z;
  }

  @Override
  public Map<String, ZPEModule> getModules() {
    return new HashMap<>();
  }

  @Override
  public boolean supportsWindows() {
    return true;
  }

  @Override
  public boolean supportsMacOs() {
    return true;
  }

  @Override
  public boolean supportsLinux() {
    return true;
  }

  @Override
  public String getName() {
    return "libSystemInfo";
  }

  @Override
  public String getVersionInfo() {
    return "1.0";
  }


  public static void main(String[] args){
    System.setProperty("jna.nosys", "true");
    System.setProperty("jna.tmpdir", "/Users/jamiebalfour/tmp");
    SystemInfo s = new SystemInfo();
    System.out.println(s.getHardware().getDiskStores());
  }
}
