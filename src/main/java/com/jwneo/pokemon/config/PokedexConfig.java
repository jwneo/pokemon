package com.jwneo.pokemon.config;

import com.jwneo.pokemon.model.Pokedex;
import com.jwneo.pokemon.repository.PokedexRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Configuration
@RequiredArgsConstructor
public class PokedexConfig {

    private final PokedexRepository pokedexRepository;

    @PostConstruct
    private void init() {
        String[] pokes = {"이상해씨A", "이상해씨B", "이상해풀", "이상해꽃", "파이리A", "파이리B", "리자드", "리자몽",
                "꼬부기A", "꼬부기B", "어니부기", "거북왕", "캐터피", "단데기", "버터플", "뿔충이", "딱충이", "독침붕",
                "구구", "피죤", "피죤투", "꼬렛", "레트라", "깨비참", "깨비드릴조", "아보", "아보크", "피카츄A", "피카츄B",
                "라이츄", "모래두지", "고지", "니드런♀", "니드리나", "니드퀸", "니드런♂", "니드리노", "니드킹", "삐삐",
                "픽시", "식스테일", "나인테일", "푸린", "푸크린", "주뱃", "골뱃", "뚜벅쵸", "냄새꼬", "라플레시아", "파라스",
                "파라섹트", "콘팡", "도나리", "디그다", "닥트리오", "나옹", "페르시온", "고라파덕", "골덕", "망키", "성원숭",
                "가디", "윈디", "발챙이", "슈륙챙이", "강챙이", "캐이시", "윤겔라", "후딘", "알통몬", "근육몬", "괴력몬",
                "모다피", "우츠동", "우츠보트", "왕눈해", "독파리", "꼬마돌", "데구리", "딱구리", "포니타", "날쌩마", "야돈",
                "야도란", "코일", "레어코일", "파오리", "두두", "두트리오", "쥬쥬", "쥬레곤", "질퍽이", "질뻐기", "셀러",
                "파르셀", "고오스", "고우스트", "팬텀", "롱스톤", "슬리프", "슬리퍼", "크랩", "킹크랩", "찌리리공", "붐볼",
                "아라리", "나시", "탕구리", "텅구리", "시라소몬", "홍수몬", "내루미", "또가스", "또도가스", "뿔카노", "코뿌리",
                "럭키", "덩쿠리", "캥카", "쏘드라", "시드라", "콘치", "왕콘치", "별가사리", "아쿠스타", "마임맨", "스라크",
                "루주라", "에레브", "마그마", "쁘사이저", "켄타로스", "잉어킹", "갸라도스", "라프라스", "메타몽", "이브이A",
                "이브이B", "샤미드", "쥬피썬더", "부스터", "폴리곤", "암나이트", "암스타", "투구", "투구푸스", "프테라",
                "잠만보A", "잠만보B", "프리져", "썬더", "파이어", "미뇽", "신뇽", "망나뇽", "뮤츠A", "뮤츠B", "뮤A", "뮤B"};

        List<Pokedex> pokedexes = new ArrayList<>();
        AtomicInteger cnt = new AtomicInteger();

        IntStream.rangeClosed(1, pokes.length).forEach(i -> {
            String name = pokes[i-1];
            if (name.contains("B")) cnt.getAndIncrement();
            String code = Integer.toString(i-cnt.get());
            pokedexes.add(new Pokedex(code, name));
        });

        pokedexRepository.saveAll(pokedexes);
    }
}
