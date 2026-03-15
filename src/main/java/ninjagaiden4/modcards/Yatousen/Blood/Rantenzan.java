package ninjagaiden4.modcards.Yatousen.Blood;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;

public class Rantenzan extends AbstractNinjaAttackCard {
    public static final String ID = ModHelper.makeID("Rantenzan");

    public Rantenzan() {
        super(
                ID,
                "Rantenzan",
                2,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY,
                14,
                1,
                0.06F,
                -11,
                13
        );
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ModHelper.Loop(0, this.magicNumber, i -> {

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
