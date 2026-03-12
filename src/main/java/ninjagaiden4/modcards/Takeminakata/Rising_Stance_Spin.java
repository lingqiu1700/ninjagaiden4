package ninjagaiden4.modcards.Takeminakata;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.actions.core;
import ninjagaiden4.helpers.ModHelper;

import static ninjagaiden4.characters.yakumo.PlayerColorEnum.YAKUMO_CARD_COLOR;
import static ninjagaiden4.modcore.Ninja4.CardFields.dismemberRate;


public class Rising_Stance_Spin extends CustomCard implements ModHelper {
    public static final String ID = "ninjagaiden4:Rising_Stance_Spin";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "lingqiu1700/ninjagaiden4/images/cards/attack/Rising_Stance_Spin.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = YAKUMO_CARD_COLOR;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public Rising_Stance_Spin() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 16;
        this.tags.add(CardTags.STRIKE);
        this.tags.add(CardTags.STARTER_STRIKE);
        this.tags.add(ninjagaiden4.modcore.Ninja4.CURSED_BLADES);
        dismemberRate.set(this,0.25F);
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
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (!monster.isDeadOrEscaped()) {
                addToBot(new core(monster, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), this));
            }
        }
    }
}
