package din.mod1;

import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class ModEventHooks {

@ForgeSubscribe
public void onEntityUpdate(LivingUpdateEvent event) {
if (event.entityLiving.isPotionActive(DinMod1.flightPotion)) {
if (event.entityLiving.worldObj.rand.nextInt(20) == 0) {
event.entityLiving.attackEntityFrom(DamageSource.generic, 2);
}
}
}
}
