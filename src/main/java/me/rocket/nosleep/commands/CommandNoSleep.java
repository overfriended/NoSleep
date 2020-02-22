package me.rocket.nosleep.commands;

import me.rocket.nosleep.NoSleep;
import me.rocket.nosleep.utils.Chat;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandNoSleep implements TabExecutor {
    Chat c = new Chat().getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            c.log("&cYou must be a player to use this command!");
        }

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("nosleep")) {
            if (player.hasPermission("nosleep.toggle")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("toggle")) {
                        c.send(player, "&aYou can " + (NoSleep.getInstance().isActive() ? "now" : "no longer").toString() + " sleep in peace!");
                        NoSleep.getInstance().setActivated(!NoSleep.getInstance().isActive());

                        return true;
                    } else {
                        c.send(player, "&cInvalid usage! Use: /nosleep <toggle | set> [key] [value]\n\n[] means optional, <> means required, | means or.");
                        return false;
                    }
                } else if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("set")) {
                        if (!player.hasPermission("nosleep.set")) {
                            c.send(player, "&cYou do not have the required permission for this command. (nosleep.set)");
                            return false;
                        }

                        if (args[1].equalsIgnoreCase("explosivepower")) {
                            if (!(StringUtils.isNumeric(args[2])) && !args[2].equalsIgnoreCase("default")) {
                                c.send(player, "&cExplosive power must be a number!");
                                return false;
                            }

                            if (args[2].equalsIgnoreCase("default")) {
                                NoSleep.getInstance().setExplosivePower("5");
                                c.send(player, "&aSet the value of explosion power back to default.");
                                return true;
                            } else {
                                NoSleep.getInstance().setExplosivePower(args[2]);
                                c.send(player, "&aSet the value of explosion power to " + args[2]);
                                return true;
                            }
                        } else {
                            c.send(player, "&cNo such property!");
                            return false;
                        }
                    } else {
                        c.send(player, "&cInvalid syntax!");
                        return false;
                    }
                } else {
                    c.send(player, "&cInvalid usage! Use: /nosleep <toggle | set> [key] [value]\n\n[] means optional, <> means required, | means or.");
                    return false;
                }
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> result = new ArrayList<String>();

        if (command.getName().equalsIgnoreCase("nosleep")) {
            if (args.length == 1) {
                result.clear();

                result.add("toggle");
                result.add("set");
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("set")) {
                    result.clear();

                    result.add("explosivepower");
                }
            } else if(args.length == 3) {
                if(args[1].equalsIgnoreCase("explosivepower")) {
                    result.clear();

                    result.add("default");
                    result.add("<number>");
                }
            }

            return result;
        }

        return null;
    }
}
