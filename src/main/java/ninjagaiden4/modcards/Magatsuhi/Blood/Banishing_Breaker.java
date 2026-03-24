package ninjagaiden4.modcards.Magatsuhi.Blood;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;
import ninjagaiden4.helpers.WeaponCard;
import ninjagaiden4.modcore.WeaponTags;

public class Banishing_Breaker extends AbstractNinjaAttackCard implements WeaponCard {
    public static final String ID = ModHelper.makeID("Banishing_Breaker");

    public Banishing_Breaker() {
        super(
                ID,
                "Banishing_Breaker",
                2,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY,
                9,
                3,
                0.35F,
                3,
                0
        );
        this.tags.add(WeaponTags.BLOOD_C);
        this.tags.add(WeaponTags.X);
        this.tags.add(WeaponTags.WEAPON_MAGATSUHI);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ModHelper.Loop(0,this.magicNumber,i -> AttackHelper.RandcoreAction(p,m,this));
        ModHelper.PAP(new DexterityPower(p,2));
    }
}
