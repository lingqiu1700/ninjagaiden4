package ninjagaiden4.modcards.Yatousen;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower;
import ninjagaiden4.actions.core;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.ModHelper;
import ninjagaiden4.helpers.WeaponCard;
import ninjagaiden4.modcore.WeaponTags;

public class Rising_Twister extends AbstractNinjaAttackCard implements WeaponCard {
    public static final String ID = ModHelper.makeID("Rising_Twister");

    public Rising_Twister() {
        super(
                ID,
                "Rising_Twister",
                1,
                CardRarity.COMMON,
                CardTarget.ENEMY,
                6,
                2,
                0.15F,
                2,
                0
        );
        this.tags.add(WeaponTags.WEAPON_YATOUSEN);
        this.tags.add(WeaponTags.W_S);
        this.tags.add(WeaponTags.Y);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ModHelper.Loop(0,this.magicNumber,i -> addToBot(new core(m,new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),this)));

        ModHelper.PAP(new FlightPower(p,1));
    }
}
