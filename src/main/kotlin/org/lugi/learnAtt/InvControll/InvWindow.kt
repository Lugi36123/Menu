package org.lugi.learnAtt.InvControll

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.jetbrains.annotations.NotNull

object InvWindow{
    /**가상 인벤토리를 만듭니다.**/
    fun createInventory(player: Player, line: Int, component: Component){
        var inv = Bukkit.createInventory(null, line * 9, component)

        invList.put(player.uniqueId, inv)

        player.openInventory(inv)
    }

    /**가상 인벤토리를 제거합니다.**/
    fun deleteInventory(p: Player){
        if (invList.get(p.uniqueId) != null){
            invList.remove(p.uniqueId)
        }else{
            return
        }
    }

    /**가상 인벤토리의 정보 값을 가져옵니다.**/
    fun getInventory(p: Player): Inventory? {
        try {
            return invList.get(p.uniqueId) as Inventory
        }catch (e: NullPointerException){
            return null
        }
    }

    /**가상 인벤토리를 엽니다.**/
    fun openInventory(p: Player){
        p.openInventory(getInventory(p) as Inventory)
    }
}