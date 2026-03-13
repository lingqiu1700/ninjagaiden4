package ninjagaiden4.modcore;

import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import basemod.BaseMod;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.Keyword;
import ninjagaiden4.characters.yakumo;
import ninjagaiden4.modcards.*;
import ninjagaiden4.modcards.Takeminakata.Spiral_Shadow;
import ninjagaiden4.modcards.Takeminakata.Blood.Takumo_Drop;
import ninjagaiden4.modcards.Weapon_Switch.Switch_Weapon;
import ninjagaiden4.modcards.skill.Just_Block;
import ninjagaiden4.relics.Yatousen;
import ninjagaiden4.relics.hitogoroshi;

import java.nio.charset.StandardCharsets;

import static com.megacrit.cardcrawl.core.Settings.language;


@SpireInitializer
public class Ninja4 implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, EditKeywordsSubscriber {

    public static final String BIG_ORB = "lingqiu1700/ninjagaiden4/images/charSelect/cars_orb.png";
    public static final String SMALL_ORB = "lingqiu1700/ninjagaiden4/images/charSelect/small_orb.png";
    public static final String BG_ATTACK_512 = "lingqiu1700/ninjagaiden4/images/512/bg_attack.png";
    public static final String BG_SKILL_512 = "lingqiu1700/ninjagaiden4/images/512/bg_skill.png";
    public static final String BG_POWER_512 = "lingqiu1700/ninjagaiden4/images/512/bg_power.png";
    public static final String BG_ATTAKC_1024 = "lingqiu1700/ninjagaiden4/images/1024/bg_attack.png";
    public static final String BG_SKILL_1024 = "lingqiu1700/ninjagaiden4/images/1024/bg_skill.png";
    public static final String BG_POWER_1024 = "lingqiu1700/ninjagaiden4/images/1024/bg_power.png";
    public static final String ENGRGY_ORB = "lingqiu1700/ninjagaiden4/images/charSelect/cost_orb.png";


    public static final Color BLOOD_BLADE = new Color(1.0F, 47.0F / 255.0F, 104.0F / 255.0F, 1.0F);
    @SpireEnum
    public static AbstractCard.CardTags CURSED_BLADES;

    public Ninja4() {
        BaseMod.subscribe(this);
        BaseMod.addColor(yakumo.PlayerColorEnum.YAKUMO_CARD_COLOR,
                BLOOD_BLADE, BLOOD_BLADE, BLOOD_BLADE, BLOOD_BLADE, BLOOD_BLADE, BLOOD_BLADE, BLOOD_BLADE,
                BG_ATTACK_512, // attackBg
                BG_SKILL_512,  // skillBg
                BG_POWER_512,  // powerBg
                SMALL_ORB,     // energyOrb
                BG_ATTAKC_1024,// attackBgPortrait
                BG_SKILL_1024, // skillBgPortrait
                BG_POWER_1024, // powerBgPortrait
                BIG_ORB,       // energyOrbPortrait
                ENGRGY_ORB     // cardEnergyOrb
        );
    }

    public static void initialize() {
        new Ninja4();
    }

    @Override
    public void receiveEditCards() {
        //  这里写添加你卡牌的代码
        BaseMod.addCard(new Strike());
        BaseMod.addCard(new Izuna_Drop());
        BaseMod.addCard(new Bloodbath_Slaughter());
        BaseMod.addCard(new Spiral_Shadow());
        BaseMod.addCard(new Takumo_Drop());
        BaseMod.addCard(new Flying_Swallow());
        BaseMod.addCard(new hayabusa_Flying_Swallow());
        BaseMod.addCard(new Ultimate_Defense());
        BaseMod.addCard(new Just_Block());
        BaseMod.addCard(new UT());
        BaseMod.addCard(new OT());
        BaseMod.addCard(new Switch_Weapon());
    }

    @Override
    public void receiveEditStrings() {
        String lang;
        if (language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        } else {
            lang = "eng";
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "lingqiu1700/ninjagaiden4/localization/" + lang + "/cards.json");
        BaseMod.loadCustomStringsFile(com.megacrit.cardcrawl.localization.CharacterStrings.class, "lingqiu1700/ninjagaiden4/localization/" + lang + "/characters.json");
    }

    // 注册角色
    @Override
    public void receiveEditCharacters() {
        // 告诉原版把角色加到选人界面
        BaseMod.addCharacter(new yakumo(CardCrawlGame.playerName),
                "lingqiu1700/ninjagaiden4/images/charSelect/button.png", // 选人界面的小按钮图标
                "lingqiu1700/ninjagaiden4/images/charSelect/portrait.png" // 选人界面的背景大图
        );
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(
                new Yatousen(),
                yakumo.PlayerColorEnum.YAKUMO_CARD_COLOR
        );
        BaseMod.addRelicToCustomPool(
                new hitogoroshi(),
                yakumo.PlayerColorEnum.YAKUMO_CARD_COLOR
        );
    }

    @Override
    public  void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang = "eng";
        if (language == Settings.GameLanguage.ZHS) {
            lang = "zhs";
        }

        String json = Gdx.files.internal("lingqiu1700/ninjagaiden4/localization/Keywords_" + lang + ".json")
                .readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                // 这个id要全小写
                BaseMod.addKeyword("ninjagaiden4", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = SpirePatch.CLASS)
    public static class CardFields {
        public static SpireField<Float> dismemberRate = new SpireField<>(() -> 0.0F);
    }

}
