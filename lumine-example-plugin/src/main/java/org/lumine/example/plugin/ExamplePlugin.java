package org.lumine.example.plugin;

import org.lumine.command.java.Command;
import org.lumine.command.java.CommandCapability;
import org.lumine.command.java.Description;
import org.lumine.command.java.Parameter;
import org.lumine.event.player.Join;
import org.lumine.plugin.java.Capability;
import org.lumine.plugin.java.JavaPlugin;
import org.lumine.plugin.java.React;
import org.lumine.plugin.java.ReactorCapability;

import com.google.common.collect.ImmutableList;

public class ExamplePlugin extends JavaPlugin {

    @Override
    public ImmutableList<Capability> capabilities() {
        return ImmutableList.of(ReactorCapability.get(), CommandCapability.get());
    }
    
    @Command("tp")
    @Description("Teleports you to a destination")
    public void teleport(@Parameter("target") @Description("The target location") String test) {
        
    }

    @React
    public void onPlayerJoin(Join join) {
    }
}
