import jamiebalfour.zpe.ZPESystemInfoObject;
import jamiebalfour.zpe.core.ZPEStructure;
import jamiebalfour.zpe.interfaces.ZPECustomFunction;
import jamiebalfour.zpe.interfaces.ZPELibrary;
import oshi.SystemInfo;

import java.util.HashMap;
import java.util.Map;

public class Plugin implements ZPELibrary {


  public Plugin(){}


  @Override
  public Map<String, ZPECustomFunction> getFunctions() {
    HashMap<String, ZPECustomFunction> arr = new HashMap<String, ZPECustomFunction>();

    return arr;
  }

  @Override
  public Map<String, Class<? extends ZPEStructure>> getObjects() {
    HashMap<String, Class<? extends ZPEStructure>> z = new HashMap<>();
    z.put("SystemInfo", ZPESystemInfoObject.class);
    return z;
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
