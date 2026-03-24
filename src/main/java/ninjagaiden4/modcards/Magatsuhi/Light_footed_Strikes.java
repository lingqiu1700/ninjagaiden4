package ninjagaiden4.modcards.Magatsuhi;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;
import ninjagaiden4.helpers.WeaponCard;
import ninjagaiden4.modcore.WeaponTags;

public class Light_footed_Strikes extends AbstractNinjaAttackCard implements WeaponCard {
    public static final String ID = ModHelper.makeID("Light_footed_Strikes");

    public Light_footed_Strikes() {
        super(
                ID,
                "Light_footed_Strikes",
                1,
                CardRarity.COMMON,
                CardTarget.ENEMY,
                6,
                5,
                0.05F,
                2,
                0
        );
        this.tags.add(WeaponTags.WEAPON_MAGATSUHI);
        this.tags.add(WeaponTags.W_S);
        this.tags.add(WeaponTags.X);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ModHelper.Loop(0,this.magicNumber,i -> {AttackHelper.RandcoreAction(p,m,this);});
    }
}
