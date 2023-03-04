/*
 * Copyright Notice for theHunterRemaster Copyright (c) at Carina Sophie Schoppe 2022 File created on 9/26/22, 11:08 PM by Carina Sophie The Latest changes made by Carina Sophie on 9/26/22, 9:28 PM All contents of "MapResetter.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is at Carina Sophie Schoppe. All rights reserved Any type of duplication, distribution, rental, sale, award, Public accessibility or other use requires the express written consent of Carina Sophie Schoppe.
 */
package de.pixels.thehunter.util.misc

import de.pixels.thehunter.util.game.Game
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.block.Block

class MapResetter(val game: Game) {

    private val blocks = mutableListOf<String>()

    fun addBlockToList(block: Block) {
        val blockString = block.type.name + ":" + block.world.name + ":" + block.x + ":" + block.y + ":" + block.z
        blocks.add(blockString)
    }

    companion object {
        fun setBlock(blockString: String) {
            val data = blockString.split(":")
            val type = Material.getMaterial(data[0])!!
            //val blockData = Bukkit.getServer().createBlockData(data[1])
            val world = Bukkit.getWorld(data[1])!!
            val x = data[2].toInt()
            val y = data[3].toInt()
            val z = data[4].toInt()

            world.getBlockAt(x, y, z).type = type
            //   world.getBlockAt(x, y, z).blockData = blockData
        }
    }


    fun resetMap() {
        blocks.reverse()
        game.gameEntities.forEach {
            it.remove()
        }
        game.gameEntities.clear()
        blocks.forEach {
            setBlock(it)
        }
        blocks.clear()
    }
}
