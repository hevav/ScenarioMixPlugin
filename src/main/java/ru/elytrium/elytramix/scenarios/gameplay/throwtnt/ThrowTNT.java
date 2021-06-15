package ru.elytrium.elytramix.scenarios.gameplay.throwtnt;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import ru.elytrium.elytramix.scenarios.Scenario;
import ru.elytrium.elytramix.scenarios.config.Configuration;

public class ThrowTNT extends Scenario {
    public ThrowTNT() {
        super("Выброс TNT", "throw_tnt", "TNT", "scenario","Возможность выбрасывать TNT", "определенным предметом", "Задача игроков - не", "попасть в воду.");
        addConfig(item_id);
        addConfig(delay);
        addConfig(velocity);
        addConfig(kill_radius);
        addConfig(player_velocity);
        addListener(new WaterListener());
        addListener(new TNTListener(this));
    }

    private final Configuration<Material> item_id = new Configuration<>("item", Material.END_ROD, "END_ROD", this, "Предмет-бросалка TNT");
    private final Configuration<Integer> delay = new Configuration<>("delay", 5, "WATCH", this, "Время взрыва TNT");
    private final Configuration<Integer> velocity = new Configuration<>("velocity", 12, "TNT", this, "Скорость полета TNT");
    private final Configuration<Integer> kill_radius = new Configuration<>("kill_radius", 3, "BARRIER", this, "Радиус работы TNT");
    private final Configuration<Integer> player_velocity = new Configuration<>("player_velocity", 3, "FEATHER", this, "Скорость игрока при отбросе");

    public void start(Player player) {

    }

    public void stop() {

    }

    public Material getItem(){
        return item_id.value();
    }

    public Integer getDelay(){
        return delay.value();
    }

    public Integer getVelocity(){
        return velocity.value();
    }

    public Integer getKillRadius(){
        return kill_radius.value();
    }

    public Integer getPlayerVelocity(){
        return player_velocity.value();
    }
}
