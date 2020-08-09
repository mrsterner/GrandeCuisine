package net.mrsterner.grandecuisine.client.renderer;


import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.Identifier;
import net.mrsterner.grandecuisine.client.model.CrabEntityModel;
import net.mrsterner.grandecuisine.entity.CrabEntity;

/*
 * A renderer is used to provide an entity model, shadow size, and texture.
 */
public class CrabEntityRenderer extends MobEntityRenderer<CrabEntity, CrabEntityModel> {

    public CrabEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new CrabEntityModel(), 0.5f);
    }

    @Override
    public Identifier getTexture(CrabEntity entity) {
        return new Identifier("grandecuisine", "textures/entity/crab.png");
    }
}
