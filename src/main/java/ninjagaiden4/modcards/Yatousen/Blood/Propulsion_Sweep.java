package ninjagaiden4.modcards.Yatousen.Blood;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;
import ninjagaiden4.helpers.WeaponCard;
import ninjagaiden4.modcore.WeaponTags;

public class Propulsion_Sweep extends AbstractNinjaAttackCard implements WeaponCard {
    public static final String ID = ModHelper.makeID("Propulsion_Sweep");

    public Propulsion_Sweep() {
        super(
                ID,
                "Propulsion_Sweep",
                2,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY,
                8,
                3,
                0.15F,
                2,
                0
        );
        this.tags.add(WeaponTags.WEAPON_YATOUSEN);
        this.tags.add(WeaponTags.BLOOD_C);
        this.tags.add(WeaponTags.Y);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ModHelper.PAP(new DexterityPower(p,4));

        ModHelper.Loop(0,this.magicNumber,i -> {
            AttackHelper.coreAction(p, m, this);

            AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(
                    m,    // 传 m，代表“排除玩家选中的这个怪”，确保一定打在另一个怪身上
                    true, // 只找活着的
                    AbstractDungeon.cardRandomRng
            );

            AttackHelper.coreAction(p, randomMonster, this);
        });
    }
}
