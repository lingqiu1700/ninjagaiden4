package ninjagaiden4.modcards.Magatsuhi.Blood;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;
import ninjagaiden4.helpers.WeaponCard;
import ninjagaiden4.modcards.Magatsuhi.Blood.Derivative.Earth_Shatter_Derivative;
import ninjagaiden4.modcore.WeaponTags;

public class Earth_Shatter extends AbstractNinjaAttackCard implements WeaponCard {
    public static final String ID = ModHelper.makeID("Earth_Shatter");

    public Earth_Shatter() {
        super(
                ID,
                "Earth_Shatter",
                1,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY,
                6,
                1,
                0.1F,
                2,
                0
        );
        this.cardsToPreview = new Earth_Shatter_Derivative();
        this.tags.add(WeaponTags.WEAPON_MAGATSUHI);
        this.tags.add(WeaponTags.BLOOD_W_S);
        this.tags.add(WeaponTags.X);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AttackHelper.coreAction(p,m,this);

        addToBot(new MakeTempCardInDrawPileAction(
                new Earth_Shatter_Derivative(), // 参数1：你要生成的衍生牌
                this.magicNumber,               // 参数2：生成的数量 (你的魔法数字)
                false,                          // 参数3：是否塞入随机位置 (设为 false)
                true,                           // 参数4：是否在屏幕中间播放生成动画 (设为 true 更有手感)
                true                            // 参数5：【关键】是否强制塞入牌库最底部 (设为 true)
        ));
    }
}
