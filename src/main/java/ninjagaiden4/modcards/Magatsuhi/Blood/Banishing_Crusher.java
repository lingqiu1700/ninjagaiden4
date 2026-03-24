package ninjagaiden4.modcards.Magatsuhi.Blood;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.actions.core;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;
import ninjagaiden4.helpers.WeaponCard;
import ninjagaiden4.modcore.WeaponTags;

public class Banishing_Crusher extends AbstractNinjaAttackCard implements WeaponCard {
    public static final String ID = ModHelper.makeID("Banishing_Crusher");

    public Banishing_Crusher() {
        super(
                ID,
                "Banishing_Crusher",
                2,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY,
                10,
                3,
                0.35F,
                4,
                0
        );
        this.tags.add(WeaponTags.WEAPON_MAGATSUHI);
        this.tags.add(WeaponTags.Y);
        this.tags.add(WeaponTags.BLOOD_C);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AttackHelper.AoeAction(p,this,(monster, info) -> new core(monster, info, this));
        AttackHelper.RandcoreAction(p,m,this);
        AttackHelper.AoeAction(p,this,(monster, info) -> new core(monster, info, this));
    }
}
