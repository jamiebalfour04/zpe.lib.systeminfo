import jamiebalfour.zpe.ZPESystemInfoObject;
import jamiebalfour.zpe.core.*;
import jamiebalfour.zpe.core.interfaces.ZPECustomFunction;
import jamiebalfour.zpe.core.interfaces.ZPELibrary;
import jamiebalfour.zpe.core.interfaces.ZPEPropertyWrapper;
import jamiebalfour.zpe.core.interfaces.ZPEType;
import jamiebalfour.zpe.core.types.ZPEString;
import oshi.SystemInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Plugin implements ZPELibrary {


  public Plugin(){}


  @Override
  public Map<String, ZPECustomFunction> getFunctions() {
    return new HashMap<>();
  }

  @Override
  public Map<String, BiFunction<ZPERuntimeEnvironment, ZPEPropertyWrapper, ZPEObject>> getObjects() {
    Map<String, BiFunction<ZPERuntimeEnvironment, ZPEPropertyWrapper, ZPEObject>> z = new HashMap<>();
    z.put("SystemInfo", ZPESystemInfoObject::new);
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
