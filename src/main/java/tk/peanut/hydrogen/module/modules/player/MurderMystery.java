package tk.peanut.hydrogen.module.modules.player;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import org.lwjgl.opengl.GL11;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventRender3D;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.module.modules.render.ESP;
import tk.peanut.hydrogen.utils.Utils;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by peanut on 05/09/2021
 */
@Info(name = "MurderMystery", category = Category.Player, description = "Draws a line to the murderer")
public class MurderMystery extends Module {

    public ArrayList<Entity> entities = new ArrayList<Entity>();

    public MurderMystery() {
        super(0x00);
    }

    @EventTarget
    public void onRender(EventRender3D e) {
        mc.theWorld.loadedEntityList.forEach(o -> {
            Entity entity = (Entity)o;
            if (!entity.isEntityAlive() && entities.contains(entity)) {
                entities.remove(entity);
            }

            if (entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entity;

                if (player != mc.thePlayer) {
                    if (player.getCurrentEquippedItem() != null) {
                        if (player.getCurrentEquippedItem().getItem() instanceof ItemSword) {
                            if (!entities.contains(entity)) {
                                //Logger.logChat(entity.getName() + " is the murderer!");
                                entities.add(entity);
                            }
                        } else if (entities.contains(entity)) {
                            entities.remove(entity);
                        }
                    }
                }
            }
        });

        mc.theWorld.loadedEntityList.forEach(o -> {
            Entity entity = (Entity)o;
            if ((entity.isEntityAlive() && entities.contains(entity))) {

                //final double posX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * e.getPartialTicks() - RenderManager.renderPosX;
                //final double posY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * e.getPartialTicks() - RenderManager.renderPosY;
                //final double posZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * e.getPartialTicks() - RenderManager.renderPosZ;

                //mc.entityRenderer.setupCameraTransform(mc.timer.renderPartialTicks, 2);
                //renderTracer(entity, posX, posY, posZ);
            }
        });
    }

    private boolean hasSword(EntityPlayer e) {
        for (int i = 0; i < 8; i++) {
            if (e.getInventory()[i].getItem() instanceof ItemSword) {
                return true;
            }
        }
        return false;
    }

}
