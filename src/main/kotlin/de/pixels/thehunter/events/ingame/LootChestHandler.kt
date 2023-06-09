/*
 * Copyright Notice for theHunterRemaster Copyright (c) at Carina Sophie Schoppe 2022 File created on 9/26/22, 11:08 PM by Carina Sophie The Latest changes made by Carina Sophie on 9/26/22, 9:50 PM All contents of "ChestHandler.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is at Carina Sophie Schoppe. All rights reserved Any type of duplication, distribution, rental, sale, award, Public accessibility or other use requires the express written consent of Carina Sophie Schoppe.
 */

package de.pixels.thehunter.events.ingame

import de.pixels.thehunter.gamestates.IngameState
import de.pixels.thehunter.util.game.management.GamesHandler
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class LootChestHandler : Listener {
    @EventHandler
    fun onChestOpen(event: PlayerInteractEvent) {
        if (!GamesHandler.playerInGames.containsKey(event.player) || GamesHandler.playerInGames[event.player]?.currentGameState !is IngameState || event.clickedBlock == null || event.clickedBlock?.type != org.bukkit.Material.BEACON)
            return
        val game = GamesHandler.playerInGames[event.player] ?: return
        if (game.gameChest.chests.containsKey(event.clickedBlock?.location)) {
            game.gameChest.chests[event.clickedBlock?.location]?.let { event.player.openInventory(it) }
        } else {
            val inventory = game.gameChest.createItemInventory()
            game.gameChest.chests[event.clickedBlock?.location ?: return] = inventory
            event.player.openInventory(inventory)
            event.player.playSound(event.player, Sound.BLOCK_CHEST_OPEN, 1f, 1f)
        }

        event.isCancelled = true
    }


}
