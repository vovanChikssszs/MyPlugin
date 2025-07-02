import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class CreativeConsume extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        
        // Проверяем, находится ли игрок в креативе
        if (player.getGameMode() == GameMode.CREATIVE) {
            // Проверяем, является ли предмет зельем
            if (event.getItem().getType() == Material.POTION) {
                // Запускаем задачу через 1 тик (моментально после выпивания)
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        // Заменяем зелье в руке игрока на пустую колбу
                        player.getInventory().setItemInMainHand(new ItemStack(Material.GLASS_BOTTLE));
                    }
                }.runTaskLater(this, 1L);
            }
        }
    }
}