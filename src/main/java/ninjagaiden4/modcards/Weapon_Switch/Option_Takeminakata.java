package ninjagaiden4.modcards.Weapon_Switch;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.actions.SwapWeaponAction;
import ninjagaiden4.relics.Takeminakata;
import ninjagaiden4.relics.Yatousen;

public class Option_Takeminakata extends CustomCard {
    public static final String ID = "ninjagaiden4:Option_Takeminakata";
    // 这里的图片可以复用武器的图片，或者随便一张技能牌的图片
    private static final String IMG_PATH = "lingqiu1700/ninjagaiden4/images/cards/skill/Switch.png";

    public Option_Takeminakata() {
        // 注意：费用设为 -2，这样在选择界面就不会显示左上角的费用数字
        super(ID, "装备：建御名方", IMG_PATH, -2, "切换武器为建御名方，并将牌组中的招式替换为对应派生。",
                CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 因为这是选项卡，永远不会被真正“打出”，所以这里留空即可
    }

    @Override
    public void upgrade() {}

    // 【核心逻辑】：当玩家在界面中点击了这张卡时，会触发这个方法！
    @Override
    public void onChoseThisOption() {
        // 调用我们之前写好的换武器 Action
        AbstractDungeon.actionManager.addToBottom(new SwapWeaponAction(Takeminakata.ID));
    }
}