package ninjagaiden4.modcards.Magatsuhi.Blood;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.actions.core;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;
import ninjagaiden4.helpers.WeaponCard;
import ninjagaiden4.modcore.WeaponTags;

public class Dragon_Trial_Orbit extends AbstractNinjaAttackCard implements WeaponCard {
    public static String ID = ModHelper.makeID("Dragon_Trial_Orbit");

    public Dragon_Trial_Orbit() {
        super(
                ID,
                "Dragon_Trial_Orbit",
                1,
                CardRarity.UNCOMMON,
                CardTarget.ALL_ENEMY,
                8,
                1,
                0.1F,
                2,
                0
        );
        this.isMultiDamage = true;
        this.tags.add(WeaponTags.WEAPON_MAGATSUHI);
        this.tags.add(WeaponTags.Y);
        this.tags.add(WeaponTags.BLOOD_F);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AttackHelper.AoeAction(p,this,(monster, info) -> new core(monster, info, this));
    }
}
