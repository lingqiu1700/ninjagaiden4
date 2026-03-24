package ninjagaiden4.modcards.Yatousen;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.actions.core;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;
import ninjagaiden4.helpers.WeaponCard;
import ninjagaiden4.modcore.WeaponTags;

public class Skewer_Feast extends AbstractNinjaAttackCard implements WeaponCard {
    public static final String ID = ModHelper.makeID("Skewer_Feast");

    public Skewer_Feast() {
        super(
                ID,
                "Skewer_Feast",
                1,
                CardRarity.COMMON,
                CardTarget.ENEMY,
                6,
                0,
                0.2F,
                4,
                0
        );
        this.tags.add(WeaponTags.WEAPON_YATOUSEN);
        this.tags.add(WeaponTags.F);
        this.tags.add(WeaponTags.Y);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AttackHelper.coreAction(p,m,this);
        double bei = this.damage * 1.5;
        addToBot(new core(m, new DamageInfo(p, (int) bei,DamageInfo.DamageType.NORMAL),this));
    }
}
