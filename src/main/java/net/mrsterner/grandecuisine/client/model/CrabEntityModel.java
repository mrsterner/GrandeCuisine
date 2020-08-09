package net.mrsterner.grandecuisine.client.model;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.mrsterner.grandecuisine.entity.CrabEntity;

import javax.swing.text.html.parser.Entity;






public class CrabEntityModel extends EntityModel<CrabEntity> {

        private final ModelPart main;
        private final ModelPart head;
        private final ModelPart leftlegs;
        private final ModelPart leftleg1;
        private final ModelPart leftleg2;
        private final ModelPart leftleg3;
        private final ModelPart bigarm;
        private final ModelPart bigclaw;
        private final ModelPart shell;
        private final ModelPart smallarm;
        private final ModelPart smallclaw;
        private final ModelPart rightlegs;
        private final ModelPart rightleg1;
        private final ModelPart rightleg2;
        private final ModelPart rightleg3;
        public CrabEntityModel() {
            textureWidth = 64;
            textureHeight = 64;
            main = new ModelPart(this);
            main.setPivot(0.0F, 24.0F, -2.0F);
            main.setTextureOffset(0, 16).addCuboid(-3.0F, -3.0F, 0.0F, 6.0F, 2.0F, 5.0F, 0.0F, false);

            head = new ModelPart(this);
            head.setPivot(0.0F, 0.0F, 0.0F);
            main.addChild(head);
            head.setTextureOffset(0, 23).addCuboid(-2.0F, -3.0F, -2.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
            head.setTextureOffset(6, 4).addCuboid(-2.0F, -6.0F, -2.0F, 1.0F, 3.0F, 0.0F, 0.0F, false);
            head.setTextureOffset(2, 4).addCuboid(-2.0F, -6.0F, -2.001F, 1.0F, 3.0F, 0.0F, 0.0F, false);
            head.setTextureOffset(4, 4).addCuboid(1.0F, -6.0F, -2.0F, 1.0F, 3.0F, 0.0F, 0.0F, false);
            head.setTextureOffset(0, 4).addCuboid(1.0F, -6.0F, -2.001F, 1.0F, 3.0F, 0.0F, 0.0F, false);

            leftlegs = new ModelPart(this);
            leftlegs.setPivot(0.0F, 0.0F, 1.0F);
            main.addChild(leftlegs);


            leftleg1 = new ModelPart(this);
            leftleg1.setPivot(3.0F, -1.0F, 0.0F);
            leftlegs.addChild(leftleg1);
            setRotationAngle(leftleg1, 0.0F, 0.2618F, 0.2618F);
            leftleg1.setTextureOffset(24, 4).addCuboid(-1.0F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, 0.0F, false);

            leftleg2 = new ModelPart(this);
            leftleg2.setPivot(3.0F, -1.0F, 2.0F);
            leftlegs.addChild(leftleg2);
            setRotationAngle(leftleg2, 0.0F, 0.0F, 0.2618F);
            leftleg2.setTextureOffset(24, 2).addCuboid(-1.0F, -0.5F, -0.8F, 5.0F, 1.0F, 1.0F, 0.0F, false);

            leftleg3 = new ModelPart(this);
            leftleg3.setPivot(3.0F, -1.0F, 3.0F);
            leftlegs.addChild(leftleg3);
            setRotationAngle(leftleg3, 0.0F, -0.2618F, 0.2618F);
            leftleg3.setTextureOffset(24, 0).addCuboid(-1.0F, -0.5F, 0.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);

            bigarm = new ModelPart(this);
            bigarm.setPivot(-4.0F, -3.0F, -1.5F);
            main.addChild(bigarm);
            setRotationAngle(bigarm, -0.0873F, 0.5236F, -0.1745F);
            bigarm.setTextureOffset(14, 24).addCuboid(-0.5F, 1.0F, -1.634F, 1.0F, 1.0F, 4.0F, 0.4F, false);

            bigclaw = new ModelPart(this);
            bigclaw.setPivot(0.3529F, 0.0546F, 1.3684F);
            bigarm.addChild(bigclaw);
            setRotationAngle(bigclaw, 0.0F, 0.2618F, 0.0F);
            bigclaw.setTextureOffset(20, 21).addCuboid(-0.8365F, 0.5F, -2.715F, 4.0F, 2.0F, 2.0F, 0.0F, false);
            bigclaw.setTextureOffset(17, 16).addCuboid(-0.8365F, 0.5F, -5.515F, 5.0F, 2.0F, 3.0F, 0.3F, false);

            shell = new ModelPart(this);
            shell.setPivot(0.0F, 0.0F, 0.0F);
            main.addChild(shell);
            shell.setTextureOffset(0, 0).addCuboid(-4.0F, -10.0F, 2.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

            smallarm = new ModelPart(this);
            smallarm.setPivot(-1.0F, -1.0F, -1.0F);
            main.addChild(smallarm);
            setRotationAngle(smallarm, 0.0F, -0.4363F, 0.0F);
            smallarm.setTextureOffset(8, 23).addCuboid(3.5F, -1.5F, -4.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

            smallclaw = new ModelPart(this);
            smallclaw.setPivot(-1.3289F, 0.0F, -0.4837F);
            smallarm.addChild(smallclaw);
            setRotationAngle(smallclaw, 0.0F, -0.2618F, 0.0F);
            smallclaw.setTextureOffset(0, 2).addCuboid(1.5926F, -1.5F, -4.3483F, 3.0F, 1.0F, 1.0F, 0.1F, false);
            smallclaw.setTextureOffset(0, 0).addCuboid(1.5789F, -1.5F, -5.5163F, 3.0F, 1.0F, 1.0F, 0.3F, false);

            rightlegs = new ModelPart(this);
            rightlegs.setPivot(0.0F, 0.0F, 1.0F);
            main.addChild(rightlegs);


            rightleg1 = new ModelPart(this);
            rightleg1.setPivot(-3.0F, -1.0F, 0.0F);
            rightlegs.addChild(rightleg1);
            setRotationAngle(rightleg1, 0.0F, -0.2618F, -0.2618F);
            rightleg1.setTextureOffset(24, 4).addCuboid(-4.0F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, 0.0F, true);

            rightleg2 = new ModelPart(this);
            rightleg2.setPivot(-3.0F, -1.0F, 2.0F);
            rightlegs.addChild(rightleg2);
            setRotationAngle(rightleg2, 0.0F, 0.0F, -0.2618F);
            rightleg2.setTextureOffset(24, 2).addCuboid(-4.0F, -0.5F, -0.8F, 5.0F, 1.0F, 1.0F, 0.0F, true);

            rightleg3 = new ModelPart(this);
            rightleg3.setPivot(-3.0F, -1.0F, 3.0F);
            rightlegs.addChild(rightleg3);
            setRotationAngle(rightleg3, 0.0F, 0.2618F, -0.2618F);
            rightleg3.setTextureOffset(24, 0).addCuboid(-4.0F, -0.5F, 0.0F, 5.0F, 1.0F, 1.0F, 0.0F, true);
        }

    @Override
    public void setAngles(CrabEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
       /* this.rightleg3.pivotY = MathHelper.sin(limbAngle * 1.0F) * -1.0F * limbDistance;
        this.rightleg2.pivotY = MathHelper.sin(limbAngle * 1.0F) * 1.0F * limbDistance;
        this.rightleg1.pivotY = MathHelper.sin(limbAngle * 1.0F) * -1.0F * limbDistance;

        this.shell.pivotX = MathHelper.sin(headYaw * 0.2f)* -0.2f*limbDistance;

        this.leftleg3.pivotY = MathHelper.sin(limbAngle * 1.0F) * -1.0F * limbDistance;
        this.leftleg2.pivotY = MathHelper.sin(limbAngle * 1.0F) * 1.0F * limbDistance;
        this.leftleg1.pivotY = MathHelper.sin(limbAngle * 1.0F) * -1.0F * limbDistance;
*/
        this.shell.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 0.6F * limbDistance;

        this.rightleg1.yaw = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * -1.2F * limbDistance;
        this.leftleg1.yaw = MathHelper.cos(limbAngle * 0.6662F) * -1.2F * limbDistance;
        this.rightleg2.yaw = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.2F * limbDistance;
        this.leftleg2.yaw = MathHelper.cos(limbAngle * 0.6662F) * 1.2F * limbDistance;
        this.rightleg3.yaw = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * -1.2F * limbDistance;
        this.leftleg3.yaw = MathHelper.cos(limbAngle * 0.6662F) * -1.2F * limbDistance;
    }


        @Override
        public void render(MatrixStack matrixStack, VertexConsumer  buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){

            main.render(matrixStack, buffer, packedLight, packedOverlay);
        }
        public void setRotationAngle(ModelPart bone, float x, float y, float z) {
            bone.pitch = x;
            bone.yaw = y;
            bone.roll = z;



        }

    }
