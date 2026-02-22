<h1>zpe.lib.systeminfo</h1>

<p>
  This is the official System Information plugin for ZPE.
</p>

<p>
  The plugin provides access to CPU, BIOS, baseboard, sensors and graphics card information.
</p>

<h2>Installation</h2>

<p>
  Place <strong>zpe.lib.systeminfo.jar</strong> in your ZPE native-plugins folder and restart ZPE.
</p>

<p>
  You can also download with the ZULE Package Manager by using:
</p>
<p>
  <code>zpe --zule install zpe.lib.systeminfo.jar</code>
</p>

<h2>Documentation</h2>

<p>
  Full documentation, examples and API reference are available here:
</p>

<p>
  <a href="https://www.jamiebalfour.scot/projects/zpe/documentation/plugins/zpe.lib.systeminfo/" target="_blank">
    View the complete documentation
  </a>
</p>

<h2>Example</h2>

<pre>

import "zpe.lib.systeminfo"

sys = new SystemInfo()

cpu = sys.get_cpu()

print(cpu.get_identifier())
print(cpu.get_physical_core_count())

cards = sys.get_graphics_cards()

for (c in cards)
    print(c.get_name())
end for
</pre>

<h2>Notes</h2>

<ul>
  <li>Uses OSHI internally.</li>
  <li>Cross-platform (Windows, macOS, Linux).</li>
</ul>
