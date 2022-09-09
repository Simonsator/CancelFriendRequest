package de.simonsator.partyandfriends.velocity.cfr;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import de.simonsator.partyandfriends.velocity.VelocityExtensionLoadingInfo;
import de.simonsator.partyandfriends.velocity.main.PAFPlugin;

import java.nio.file.Path;

@Plugin(id = "cancelfriendrequest", name = "CancelFriendRequest", version = "1.0.4-SNAPSHOT",
		url = "https://www.spigotmc.org/resources/cancel-friend-requests-for-party-and-friends.34567/",
		description = "An add-on for party and friends to cancel friend requests"
		, authors = {"JT122406", "Simonsator"}, dependencies = {@Dependency(id = "partyandfriends")})
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
					"1.0.4-RELEASE", "JT122406, Simonsator"));
		}
	}
