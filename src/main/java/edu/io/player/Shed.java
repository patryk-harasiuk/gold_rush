package edu.io.player;

import java.util.Stack;

public class Shed {
    private final Stack<Tool> tools = new Stack<>();
    private final NoTool noTool = new NoTool();

    public boolean isEmpty() {
        return this.tools.isEmpty();
    }

    public void add(Tool tool) {
        if (tool == null) {
            throw new IllegalArgumentException("Niepoprawna wartość dla narzędzia");
        }
        tools.push(tool);
    }

    public Tool getTool() {
        return tools.isEmpty() ? noTool : tools.peek();
    }

    public void dropTool() {
        if (!tools.isEmpty()) {
            tools.pop();
        }
    }
}
