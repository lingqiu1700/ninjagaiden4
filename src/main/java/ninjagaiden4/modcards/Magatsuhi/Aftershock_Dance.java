package ninjagaiden4.modcards.Magatsuhi;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;
import ninjagaiden4.helpers.WeaponCard;
import ninjagaiden4.modcore.WeaponTags;

public class Aftershock_Dance extends AbstractNinjaAttackCard implements WeaponCard {
    public static final String ID = "Aftershock_Dance";

    public Aftershock_Dance() {
        super(
                ID,
                "Aftershock_Dance",
                1,
                CardRarity.COMMON,
                CardTarget.ENEMY,
                2,
                4,
                0.04F,
                1,
                0
        );
        this.tags.add(WeaponTags.WEAPON_MAGATSUHI);
        this.tags.add(WeaponTags.F);
        this.tags.add(WeaponTags.Y);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ModHelper.Loop(0,this.magicNumber, i -> {
            AttackHelper.RandcoreAction(p,m,this);
        });
    }
}
