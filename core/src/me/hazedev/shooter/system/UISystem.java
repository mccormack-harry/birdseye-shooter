package me.hazedev.shooter.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import me.hazedev.shooter.Mapper;
import me.hazedev.shooter.World;
import me.hazedev.shooter.component.HealthComponent;
import me.hazedev.shooter.component.ShooterComponent;
import me.hazedev.shooter.event.listener.WindowResizeListener;

/*
Score
Health/MaxHealth
 */
public class UISystem extends EntitySystem {

    private final World world;
    private final Stage stage;
    private final Label scoreLabel;
    private final Label healthLabel;

    public UISystem(World world) {
        this.world = world;
        stage = new Stage(new FitViewport(512, 512));
        Table table = new Table();
//        table.setDebug(true);
        table.setFillParent(true);
        stage.addActor(table);

        Label.LabelStyle labelStyle = new Label.LabelStyle(world.assets.getFont(), Color.WHITE);

        scoreLabel = new Label("0", labelStyle);
        healthLabel = new Label("0", labelStyle);

        table.add(scoreLabel).expand().top().right().pad(8);
        table.row();
        table.add(healthLabel).expand().bottom().right().pad(8);


//        score = new Label("0", new Label.LabelStyle(world.assets.getFont(), Color.WHITE));
//        score.setPosition(0, 0);
//        world.stage.addActor(score);

        world.signaller.windowResizeSignal.add(new WindowResizeListener(stage.getViewport()));
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        Entity shooterEntity = world.getEntitiesFor(Family.all(ShooterComponent.class).get()).get(0);

        ShooterComponent shooter = Mapper.SHOOTER.get(shooterEntity);
        if (shooter != null)
        scoreLabel.setText(shooter.kills);

        HealthComponent health = Mapper.HEALTH.get(shooterEntity);
        if (health != null)
            healthLabel.setText((int) health.health);

        stage.getViewport().apply();
        stage.act(delta);
        stage.draw();


//        camera.update();
//        batch.setProjectionMatrix(camera.combined);
//        batch.begin();
//        world.assets.getFont().draw(batch, String.format("%d", shooter.kills), 0, 0);
//        batch.end();

    }

}
