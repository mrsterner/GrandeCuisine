package net.mrsterner.grandecuisine.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;

public class ExampleGui extends LightweightGuiDescription {
    public ExampleGui(){
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(300, 200);

        WLabel label = new WLabel("Hello");
        root.add(label, 1, 1);
    }
}
