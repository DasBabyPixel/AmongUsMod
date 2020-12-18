package de.pixel.amongus.client;

import de.pixel.amongus.client.protocol.Packet;
import de.pixel.amongus.client.protocol.PacketHandler;
import de.pixel.amongus.client.version.VersionChecker;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = AmongUs.MODID, version = AmongUs.VERSION)
public class AmongUs {
	public static final String MODID = "amongus";
	public static final String VERSION = "1.0";
	
	public static SimpleNetworkWrapper network;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		VersionChecker.startCheck();
		network = NetworkRegistry.INSTANCE.newSimpleChannel(MODID.toUpperCase());
		network.registerMessage(new PacketHandler(), Packet.class, 0, Side.SERVER);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {

	}
}
