package org.lugi.learnAtt

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.PlayerSwapHandItemsEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.lugi.learnAtt.InvControll.Window
import org.lugi.learnAtt.InvControll.Window.createInventory
import org.lugi.learnAtt.InvControll.Window.deleteInventory
import org.lugi.learnAtt.InvControll.Window.getInventory
import org.lugi.learnAtt.InvControll.Window.openInventory
import org.lugi.learnAtt.InvControll.Window.setSlot
import org.lugi.learnAtt.InvControll.Window.updateInventory
import org.lugi.learnAtt.InvControll.invList

class LearnAtt : JavaPlugin(){

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

                                createInventory(e.player, 5, Component.text("메뉴"))

                                Window.apply {
                                    val item = ItemStack(Material.GRAY_STAINED_GLASS_PANE).apply {
                                        itemMeta = itemMeta.apply {
                                            displayName(
                                                Component.text("야생").decoration(TextDecoration.ITALIC, false)
                                            )
                                        }
                                    }

                                    setSlot( e.player, 1, 1, item )
                                    setSlot( e.player, 2, 1, item )
                                    setSlot( e.player, 3, 1, item )
                                    setSlot( e.player, 1, 2, item )
                                    setSlot( e.player, 2, 2, item )
                                    setSlot( e.player, 3, 2, item )
                                    setSlot( e.player, 1, 3, item )
                                    setSlot( e.player, 2, 3, item )
                                    setSlot( e.player, 3, 3, item )
                                }

                                openInventory(e.player)
                            }
                        }
                    }
                }, serverInstance)

            registerEvents(
                object : Listener {
                    @EventHandler
                    fun onClose(e: InventoryCloseEvent) {
                        if (getInventory(e.player as Player) == e.inventory){
                            deleteInventory(e.player as Player)
                        }
                    }
                }, serverInstance
            )

            registerEvents(
                object : Listener {
                    @EventHandler
                    fun onClick(e: InventoryClickEvent){
                        if (getInventory(e.whoClicked as Player) == e.inventory){
                            e.isCancelled = true
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
