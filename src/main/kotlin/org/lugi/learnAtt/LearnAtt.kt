package org.lugi.learnAtt

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.SkullType
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.block.Biome
import org.bukkit.block.Block
import org.bukkit.block.Skull
import org.bukkit.block.data.BlockData
import org.bukkit.entity.Player
import org.bukkit.entity.Pose
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.PlayerChangedMainHandEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerSwapHandItemsEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.lugi.learnAtt.InvControll.InvWindow
import org.lugi.learnAtt.InvControll.invList
import java.util.UUID

class LearnAtt : JavaPlugin() {

    var serverInstance: JavaPlugin = this

    override fun onEnable() {
        // Plugin startup logic

        server.pluginManager.apply {
            registerEvents(
                object : Listener {
                    @EventHandler
                    fun onFKey(e: PlayerSwapHandItemsEvent) {
                        e.player.apply {
                            if (this.world.name == "world") {
                                if (this.isSneaking == false) return

                                e.isCancelled = true

                                InvWindow.createInventory(e.player, 6, Component.text("메뉴"))
                                InvWindow.openInventory(e.player)
                            }
                        }
                    }
                }, serverInstance)

            registerEvents(
                object : Listener {
                    @EventHandler
                    fun onClose(e: InventoryCloseEvent) {
                        if (InvWindow.getInventory(e.player as Player) == e.inventory){
                            InvWindow.deleteInventory(e.player as Player)
                        }
                    }
                }, serverInstance)

            registerEvents(
                object : Listener {
                    @EventHandler
                    fun onClick(e: InventoryClickEvent){
                        if (InvWindow.getInventory(e.whoClicked as Player) == e.inventory){

                        }
                    }
                }
                ,serverInstance
            )
        }
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }


}
