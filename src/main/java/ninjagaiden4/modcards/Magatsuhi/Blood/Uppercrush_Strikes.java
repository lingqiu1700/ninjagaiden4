package ninjagaiden4.modcards.Magatsuhi.Blood;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;
import ninjagaiden4.helpers.WeaponCard;
import ninjagaiden4.modcards.Magatsuhi.Blood.Derivative.Uppercrush_Strikes_Derivative;
import ninjagaiden4.modcore.WeaponTags;

public class Uppercrush_Strikes extends AbstractNinjaAttackCard implements WeaponCard {
    public static final String ID = ModHelper.makeID("Uppercrush_Strikes");

    public Uppercrush_Strikes() {
        super(
                ID,
                "Uppercrush_Strikes",
                1,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY,
                2,
                3,
                0.1F,
                1,
                0
        );
        this.tags.add(WeaponTags.WEAPON_MAGATSUHI);
        this.tags.add(WeaponTags.BLOOD_W_S);
        this.tags.add(WeaponTags.Y);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AttackHelper.coreAction(p,m,this);

        ModHelper.MakeCardAction(new Uppercrush_Strikes_Derivative(),
                1);
    }
}
