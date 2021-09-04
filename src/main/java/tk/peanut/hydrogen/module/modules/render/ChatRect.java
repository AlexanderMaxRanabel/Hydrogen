package tk.peanut.hydrogen.module.modules.render;

import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;

/**
 * Created by peanut on 28/07/2021
 */
@Info(name = "NoChatRect", category = Category.Render, description = "Removes the chat background")
public class ChatRect extends Module {

    public ChatRect() {
        super(0x00);
    }

}