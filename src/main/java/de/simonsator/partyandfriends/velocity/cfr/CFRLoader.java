package de.simonsator.partyandfriends.velocity.cfr;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import de.simonsator.partyandfriends.velocity.VelocityExtensionLoadingInfo;
import de.simonsator.partyandfriends.velocity.main.PAFPlugin;

import java.nio.file.Path;

public class CFRLoader {

	private final Path folder;

	@Inject
	public CFRLoader(@DataDirectory final Path pFolder) {
		folder = pFolder;
	}

	@Subscribe
	public void onProxyInitialization(ProxyInitializeEvent event) {
		PAFPlugin.loadExtension(new VelocityExtensionLoadingInfo(new CFRMain(folder),
				"cancelfriendrequest",
				"CancelFriendRequest",
				"1.0.6-RELEASE", "JT122406, Simonsator"));
	}
}
