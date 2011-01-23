package org.lumine.command.java;


import com.google.common.base.Objects;

public class ExamplePOJO {

    private boolean commandHandled = false;
    
    public boolean commandHandled() {
        return commandHandled;
    }
    
    public void reset() {
        commandHandled = false;
    }
    
    @Command
    public void simple() {
        System.out.println("Hello, simple!");
        commandHandled = true;
    }
    
    @Command("rtp")
    @Description("Rich teleportation with descriptions.")
    public void richTeleport(
            @Parameter("from") @Description("Description for from.") String f,
            @Parameter("to") @Description("Description for to.") String t) {
        System.out.println("Teleporting from: " + f + " to: " + t);
        commandHandled = true;
    }

    @Command("tp")
    public void teleport(@Parameter("from") String f, @Parameter("to") String t) {
        System.out.println("Teleporting from: " + f + " to: " + t);
        commandHandled = true;
    }
    
    @Command
    public Object modify() {
        return new Modify();
    }
    
    private class Modify {
        @SuppressWarnings("unused")
        @Command
        public void users() {
            System.out.println("Modifying users");
            commandHandled = true;
        }
        @SuppressWarnings("unused")
        @Command
        @Description("Modifies the world")
        public void world(@Parameter("location") @Description("The location to modify") String location) {
            System.out.println("Modifying location: " + location);
            commandHandled = true;
        }
    }
    
    public String toString() {
        return Objects.toStringHelper(this).toString();
    }
}
