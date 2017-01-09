package de.simonsator.partyandfriends.cfr;

import de.simonsator.partyandfriends.api.friends.abstractcommands.FriendSubCommand;
import de.simonsator.partyandfriends.api.pafplayers.OnlinePAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayerManager;
import net.md_5.bungee.config.Configuration;

/**
 * @author simonbrungs
 * @version 1.0.0 09.01.17
 */
public class CancelCommand extends FriendSubCommand {
	private final Configuration CONFIG;

	protected CancelCommand(String[] pCommands, int pPriority, String pHelp, Configuration pConfig) {
		super(pCommands, pPriority, pHelp);
		CONFIG = pConfig;
	}

	@Override
	public void onCommand(OnlinePAFPlayer pPlayer, String[] args) {
		if (!isPlayerGiven(pPlayer, args))
			return;
		PAFPlayer toCancel = PAFPlayerManager.getInstance().getPlayer(args[1]);
		if (toCancel == null) {
			sendError(pPlayer, CONFIG.getString("Message.NeverSendAFriendRequest"));
			return;
		}
		if (toCancel.hasRequestFrom(pPlayer)) {
			toCancel.denyRequest(pPlayer);
			pPlayer.sendMessage(CONFIG.getString("Message.Canceled"));
			return;
		}
		sendError(pPlayer, CONFIG.getString("Message.NeverSendAFriendRequest"));
	}
}
