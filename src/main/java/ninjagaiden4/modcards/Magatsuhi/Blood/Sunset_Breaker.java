package ninjagaiden4.modcards.Magatsuhi.Blood;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower;
import ninjagaiden4.actions.core;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;
import ninjagaiden4.helpers.WeaponCard;
import ninjagaiden4.modcore.WeaponTags;
import ninjagaiden4.powers.Flight_Down;

public class Sunset_Breaker extends AbstractNinjaAttackCard implements WeaponCard {
    public static final String ID = ModHelper.makeID("Sunset_Breaker");

    public Sunset_Breaker() {
        super(
                ID,
                "Sunset_Breaker",
                2,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY,
                8,
                4,
                0.15F,
                2,
                0
        );
        this.tags.add(WeaponTags.WEAPON_MAGATSUHI);
        this.tags.add(WeaponTags.X);
        this.tags.add(WeaponTags.BLOOD_SPACE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AttackHelper.AoeAction(p,this,(monster, info) -> new core(monster, info, this));
        ModHelper.PAP(new FlightPower(p,1));
        ModHelper.PAP(new Flight_Down(p,1));
        ModHelper.Loop(0,this.magicNumber,i -> AttackHelper.RandcoreAction(p,m,this));
    }
}
