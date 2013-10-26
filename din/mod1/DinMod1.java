package din.mod1;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import din.mod1.lib.Reference;
import din.mod1.proxy.CommonProxy;
/*
 * This here determines the mod info, like version and name and ID
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class DinMod1 {
	@Instance(Reference.MOD_ID)
	
	/*
	 * REGISTER YOUR SHIT DIN
	 * 
	 * 
	 * ALWAYS REGISTER YOU SHIT... SERIOUSLY DO IT!
	 * 
	 * 
	 * 
	 */
	public static DinMod1 instance;
	public static Item energyBullDrink;
	public static Item steroids;
	public static Potion flightPotion;
	@SidedProxy(clientSide = Reference.ClientP, serverSide = Reference.ServerP)
	public static CommonProxy proxy;
    /***
     * This is code that is executed prior to your mod being initialized into of Minecraft
     * Examples of code that could be run here;
     * 
     * Initializing your items/blocks (you must do this here)
     * Setting up your own custom logger for writing log data to
     * Loading your language translations for your mod (if your mod has translations for other languages)
     * Registering your mod's key bindings and sounds
     * 
     * @param event The Forge ModLoader pre-initialization event
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    //Beggining of the potion area
    	Potion[] potionTypes = null;

    	for (Field f : Potion.class.getDeclaredFields()) {
    	f.setAccessible(true);
    	try {
    	if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
    	Field modfield = Field.class.getDeclaredField("modifiers");
    	modfield.setAccessible(true);
    	modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

    	potionTypes = (Potion[])f.get(null);
    	final Potion[] newPotionTypes = new Potion[256];
    	System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
    	f.set(null, newPotionTypes);
    	}
    	}
    	catch (Exception e) {
    	System.err.println("That's weird. Must be a bug.Bugger.HA!GET IT?NO? Whatever, report it!");
    	System.err.println(e);
    	}
    	}

    	MinecraftForge.EVENT_BUS.register(new ModEventHooks());
    	
    	
   //End of the potion area
    	

    }		
    
    /***
     * This is code that is executed when your mod is being initialized in Minecraft
     * Examples of code that could be run here;
     * 
     * Registering your GUI Handler
     * Registering your different event listeners
     * Registering your different tile entities
     * Adding in any recipes you have 
     * 
     * @param event The Forge ModLoader initialization event
     */
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	/*GameRegistry.addShapelessRecipe(new ItemStack(Item.item(or block),quan ), new ItemStack(Block.dirt)); */
    	energyBullDrink = (new ItemRedBullDrink(3500));
    	LanguageRegistry.addName(energyBullDrink, "§6Energy drink");
    	steroids = (new ItemSteroids(3501));
    	LanguageRegistry.addName(steroids, "§1Steroids");
    	GameRegistry.addRecipe(new ItemStack(steroids), new Object[]{
            "ZCZ",
            "CXC",
            "ZCZ",
            'X', Item.appleGold, 'C', Item.sugar, 'Z', Item.magmaCream
     });
    	
    	
    	//This is TESTING CODE for a potion. Things might fail big time
    	
    	flightPotion = (new PotionFlight(32, false, 0)).setIconIndex(0, 0).setPotionName("potion.flightPotion");
    
    	
  //  	End of Flight potion area, Again, a test

    	
    	}
    	
    	
    
   
    
    /***
     * This is code that is executed after all mods are initialized in Minecraft
     * This is a good place to execute code that interacts with other mods (ie, loads an addon module
     * of your mod if you find a particular mod).
     * 
     * @param event The Forge ModLoader post-initialization event
     */
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        
    }
}
