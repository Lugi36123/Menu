package org.lugi.learnAtt.InvControll

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

object Window{
    /**가상 인벤토리를 만듭니다.**/
    fun createInventory(player: Player, line: Int, component: Component){
        var inv = Bukkit.createInventory(null, line * 9, component)

        invList.put(player.uniqueId, inv)
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

    /**가상 인벤토리를 업데이트 합니다.**/
    fun updateInventory(p: Player, i: Inventory){
        invList.replace(p.uniqueId, i)
    }

    /**가상 인벤토리의 슬롯을 조정합니다.**/
    fun setSlot(p: Player, x: Int, y: Int, i : ItemStack){
        getInventory(p).apply {
            this?.setItem(XPlusY(x, y), i)
        }
    }

    private fun XPlusY(x: Int, y: Int): Int{
        if ((x + y) != 0){
            return x + (y * 9)
        }

        return 0
    }
}