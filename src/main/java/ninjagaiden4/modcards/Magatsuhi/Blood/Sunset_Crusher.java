package ninjagaiden4.modcards.Magatsuhi.Blood;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.actions.core;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;
import ninjagaiden4.helpers.WeaponCard;
import ninjagaiden4.modcore.WeaponTags;

public class Sunset_Crusher extends AbstractNinjaAttackCard implements WeaponCard {
    public static final String ID = ModHelper.makeID("Sunset_Crusher");

    public Sunset_Crusher() {
        super(
                ID,
                "Sunset_Crusher",
                2,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY,
                15,
                1,
                0.5F,
                15,
                0
        );
        this.tags.add(WeaponTags.WEAPON_MAGATSUHI);
        this.tags.add(WeaponTags.Y);
        this.tags.add(WeaponTags.BLOOD_SPACE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ModHelper.Loop(0, this.magicNumber, i -> {
            AttackHelper.AoeAction(p, this, (monster, info) -> new core(monster, info, this));
        });
        if (Math.random() < 0.7) {
            addToBot(new StunMonsterAction(m, p));
        }
    }
}
