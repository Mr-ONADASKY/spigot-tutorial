package com.ninjawulf98.quartermaster.commands.subcommands;

import com.ninjawulf98.quartermaster.QuarterMaster;
import com.ninjawulf98.quartermaster.commands.SubCommand;
import com.ninjawulf98.quartermaster.utils.LockMenuSystem;
import org.bukkit.entity.Player;

public class ManagerCommand extends SubCommand {

    @Override
    public String getName() {
        return "manage";
    }

    @Override
    public String getDescription() {
        return "Manage your locks";
    }

    @Override
    public String getSyntax() {
        return "/qm manage";
    }

    @Override
    public void perform(Player p, String[] args) {
        LockMenuSystem lockMenuSystem = QuarterMaster.getPlayerMenuSystem(p);

        lockMenuSystem.showLocksListGUI();
    }
}
