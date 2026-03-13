package ninjagaiden4.modcards.Takeminakata.Blood;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import ninjagaiden4.actions.core;
import ninjagaiden4.helpers.ModHelper;

import static ninjagaiden4.characters.yakumo.PlayerColorEnum.YAKUMO_CARD_COLOR;
import static ninjagaiden4.modcore.Ninja4.CardFields.dismemberRate;


public class Takumo_Drop extends CustomCard implements ModHelper {
    public static final String ID = "ninjagaiden4:Takumo_Drop";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "lingqiu1700/ninjagaiden4/images/cards/attack/Takumo_Drop.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = YAKUMO_CARD_COLOR;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Takumo_Drop() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 15;
        this.magicNumber = this.baseMagicNumber = 3;
        this.tags.add(ninjagaiden4.modcore.Ninja4.CURSED_BLADES);
        dismemberRate.set(this,0.2F);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(5);
            this.upgradeMagicNumber(1);

            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            addToBot(new core(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), this));
            if (Math.random() < 0.3) {
                addToBot(new StunMonsterAction(m, p));
            }
        }
        ModHelper.PAP(new DexterityPower(p,2));
    }
}
