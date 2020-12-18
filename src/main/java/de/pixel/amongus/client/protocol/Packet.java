package de.pixel.amongus.client.protocol;

import com.google.gson.JsonObject;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class Packet implements IMessage {

	private JsonObject object;
	
	public Packet(JsonObject object) {
		this.object = object;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		
	}

	@Override
	public void toBytes(ByteBuf buf) {

	}
	
	public JsonObject getObject() {
		return object;
	}
}
