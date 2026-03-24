package ninjagaiden4.modcards.Yatousen.Blood;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;
import ninjagaiden4.helpers.WeaponCard;
import ninjagaiden4.modcore.WeaponTags;

public class Ballista_Thrust extends AbstractNinjaAttackCard implements WeaponCard {
    public static final String ID = ModHelper.makeID("Ballista Thrust");

    public Ballista_Thrust() {
        super(
                ID,
                "Ballista_Thrust",
                2,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY,
                2,
                16,
                0.06F,
                1,
                4
        );
        this.tags.add(WeaponTags.WEAPON_YATOUSEN);
        this.tags.add(WeaponTags.BLOOD_W_S);
        this.tags.add(WeaponTags.X);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ModHelper.Loop(0,this.magicNumber,i -> AttackHelper.coreAction(p,m,this));
        addToBot(new StunMonsterAction(m,p));
    }
}
