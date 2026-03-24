package ninjagaiden4.modcards.Magatsuhi.Blood;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;
import ninjagaiden4.helpers.WeaponCard;
import ninjagaiden4.modcore.WeaponTags;

public class Dragon_Head_Orbit extends AbstractNinjaAttackCard implements WeaponCard {
    public static String ID = ModHelper.makeID("Dragon_Head_Orbit");

    public Dragon_Head_Orbit() {
        super(
                ID,
                "Dragon_Head_Orbit",
                1,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY,
                8,
                1,
                0.1F,
                2,
                0
        );
        this.tags.add(WeaponTags.BLOOD_F);
        this.tags.add(WeaponTags.WEAPON_MAGATSUHI);
        this.tags.add(WeaponTags.X);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ModHelper.Loop(0,this.magicNumber,i -> AttackHelper.coreAction(p,m,this));
    }
}
