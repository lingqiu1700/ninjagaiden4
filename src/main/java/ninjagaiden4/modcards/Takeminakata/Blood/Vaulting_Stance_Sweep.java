package ninjagaiden4.modcards.Takeminakata.Blood;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import ninjagaiden4.actions.core;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;

import static ninjagaiden4.characters.yakumo.PlayerColorEnum.YAKUMO_CARD_COLOR;
import static ninjagaiden4.modcore.Ninja4.CardFields.dismemberRate;


public class Vaulting_Stance_Sweep extends CustomCard implements ModHelper {
    public static final String ID = "ninjagaiden4:Vaulting_Stance_Sweep";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "lingqiu1700/ninjagaiden4/images/cards/attack/Vaulting_Stance_Sweep.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = YAKUMO_CARD_COLOR;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public Vaulting_Stance_Sweep() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 16;
        this.tags.add(ninjagaiden4.modcore.Ninja4.CURSED_BLADES);
        dismemberRate.set(this, 0.25F);
        this.isMultiDamage = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(8);

            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AttackHelper.AoeAction(p,this,(monster, info) -> new core(monster, info, this));
        ModHelper.PAP(new DexterityPower(p,2));
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDeadOrEscaped()) {
                if (Math.random() < 0.4) { // 每个怪 40% 独立概率
                    addToBot(new StunMonsterAction(monster, p));
                }
            }
        }
    }
}