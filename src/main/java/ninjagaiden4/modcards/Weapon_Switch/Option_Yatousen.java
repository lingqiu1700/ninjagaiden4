package ninjagaiden4.modcards.Weapon_Switch;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.actions.SwapWeaponAction;

public class Option_Yatousen extends CustomCard {
    public static final String ID = "ninjagaiden4:Option_Yatousen";
    private static final String IMG_PATH = "lingqiu1700/ninjagaiden4/images/cards/skill/Switch.png";

    public Option_Yatousen() {
        super(ID, "装备：降魔夜刀穿", IMG_PATH, -2, "切换武器为降魔夜刀穿，并将牌组中的招式替换为对应派生。",
                CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {}

    @Override
    public void upgrade() {}

    @Override
    public void onChoseThisOption() {
        AbstractDungeon.actionManager.addToBottom(new SwapWeaponAction("ninjagaiden4:Yatousen"));
    }
}