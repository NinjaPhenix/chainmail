package ninjaphenix.chainmail.mixins;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import ninjaphenix.chainmail.api.blockentity.ExpandedBlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(World.class)
public abstract class WorldMixin
{
    @Shadow @Final protected List<BlockEntity> unloadedBlockEntities;

    @Inject(method="addBlockEntity", at=@At(value = "FIELD", target = "Lnet/minecraft/world/World;isClient:Z"))
    private void addBlocKEntity(BlockEntity be, CallbackInfoReturnable<Boolean> cir) {
        ((ExpandedBlockEntity) be).onLoad();
    }

    @Inject(method="tickBlockEntities", at=@At(value="INVOKE", target = "Ljava/util/List;removeAll(Ljava/util/Collection;)Z", ordinal = 0))
    private void tickBlockEntities(CallbackInfo ci) {
        unloadedBlockEntities.forEach(be -> {
            ((ExpandedBlockEntity) be).onUnload();
            System.out.println("Unloading BE: " + Registry.BLOCK_ENTITY_TYPE.getId(be.getType()));
        });
    }
}
